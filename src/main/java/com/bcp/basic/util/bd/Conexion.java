/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bcp.basic.util.bd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import com.bcp.basic.util.AuditoriaGeneralID;
import com.bcp.basic.util.Tiempo;
import com.bcp.basic.util.UtilException;
import com.bcp.basic.util.UtilHibernate;

/**
 *
 * @author jen
 */
public class Conexion {

    final Logger logger = Logger.getLogger(Conexion.class.getName());
    public SessionFactory sessionFactory;
    public Session session;
    public Transaction transaction;
    public Query query;
    public ProcedureCall procedureCall;
    public int out = AuditoriaGeneralID.OUT_SUCCESS;
    public SQLQuery sQLQuery;
    public String StringSql;
    public List<String> parametrosName;
    public List<String> parametrosValue;
    public List<Type> parametrosType;
    public int TIPO_SQL_QUERY = 1;
    public int TIPO_QUERY = 2;
    public int TIPO = 0;
    private String[] PARAMETROS;
    private List<String> parametros;
    public List<Param> params;
    public static final int BD_DASHBOARD = 1;
    public static final int BD_PEOPLEFORCE = 0;
    public static final int BD_MAPA = 2;
    //private int BD = BD_PEOPLEFORCE;
    private int BD = BD_MAPA;

    public Conexion() {

    }

    public Conexion(int bd) {
        this.BD = bd;
    }

    public void iniciarConsulta() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void iniciarTransacion() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        exception = null;
        parametrosName = null;
        parametrosValue = null;
        parametrosType = null;

    }

    public ProcedureCall createStoredProcedureCall(String sql) {
        return session.createStoredProcedureCall(sqlDataBase(sql));
    }

    private String sqlDataBase(String sql) {
        if (BD == BD_MAPA) {
            return sql;

        } else {
            return "BD_PEOPLEFORCE." + sql;
        }

    }

    public void commit() {
        transaction.commit();
    }

    public void rollback() {
        transaction.rollback();
    }

    public void cerrarSession() {
        if (session.isOpen()) {
            session.close();
        }
    }

    public SQLQuery execSQLQuery() {
        if (getParametros().size() > 0) {
            this.StringSql = StringSql.concat(" " + getParametros().toString());
        }
        return session.createSQLQuery(StringSql);
    }

    public List execSQLQueryList(Class target) {
        if (getParametros().size() > 0) {
            this.StringSql = StringSql.concat(" " + Arrays.toString(getParametros().toArray()));
            this.StringSql = this.StringSql.replace("[", "").replace("]", "");
        }
        return session.createSQLQuery(StringSql).setResultTransformer(Transformers.aliasToBean(target)).list();
    }

    public Object execSQLQueryObject(Class target) {
        if (getParametros().size() > 0) {
            this.StringSql = StringSql.concat(" " + Arrays.toString(getParametros().toArray()));
            this.StringSql = this.StringSql.replace("[", "").replace("]", "");
        }
        return session.createSQLQuery(StringSql).setResultTransformer(Transformers.aliasToBean(target)).uniqueResult();
    }

    public void createSQLQuery(String sql) {
        String script = "{ call " + sql + "(" + HibernateUtil.crearParametros(PARAMETROS) + ")}";
        StringSql = sql;
        if (TIPO == TIPO_QUERY) {
            query = session.createSQLQuery(script);
        } else if (TIPO == TIPO_SQL_QUERY) {
            sQLQuery = session.createSQLQuery(script);
        }
        parametrosName = null;
        parametrosValue = null;
        parametrosType = null;
    }

    public void setParameter(int count, Object o, Type type) {
        getParametrosName().add(PARAMETROS[count]);
        if (type == StandardBasicTypes.INTEGER) {
            getParametrosValue().add("" + (Integer) o);
        } else if (type == StandardBasicTypes.STRING) {
            getParametrosValue().add((String) o);
        } else if (type == StandardBasicTypes.DATE) {
            getParametrosValue().add(Tiempo.parseToDate((Date) o, Tiempo.ST_YYMMDD));
        } else if (type == StandardBasicTypes.BOOLEAN) {
            getParametrosValue().add("" + (Boolean) o);
        } else if (type == StandardBasicTypes.DOUBLE) {
            getParametrosValue().add("" + (Double) o);
        } else if (type == StandardBasicTypes.BINARY) {
            getParametrosValue().add("BINARY");
        } else {
            getParametrosValue().add((String) o);
        }

        if (TIPO == TIPO_SQL_QUERY) {
            sQLQuery.setParameter(PARAMETROS[count], o, type);
        } else if (TIPO == TIPO_QUERY) {
            query.setParameter(PARAMETROS[count], o, type);
        }
        getParametrosType().add(type);
    }
    public Exception exception;

    public void executeQuery() {
        if (TIPO == TIPO_SQL_QUERY) {
            sQLQuery.executeUpdate();
        } else if (TIPO == TIPO_QUERY) {
            query.executeUpdate();
        }
    }


    public void printLogProcedureCall(String usuario) {
        try {
            String parametross = UtilHibernate.logTransaction(procedureCall);
            System.out.println("------------------------------");
            System.out.println("usuario: " + usuario);
            System.out.println(parametross);
        } catch (Exception e) {
            logger.error("ErrorLOG:", e);
        } finally {
            if (exception != null) {
                exception.printStackTrace();
            }
            PARAMETROS = null;
            parametrosName = null;
            parametrosValue = null;
            parametrosType = null;
        }
    }

    public void printLogSQLQuery(Logger logger, String usuario) {
        try {
            String consulta = StringSql;
            String ejecucion = "";
            int countParam = PARAMETROS.length;
            for (int i = 0; i < countParam; i++) {
                String parametro = "@" + getParametrosName().get(i) + "=";
                if (getParametrosType().get(i) == StandardBasicTypes.STRING) {
                    parametro = parametro + UtilHibernate.stringNULL_CORT(getParametrosValue().get(i));
                } else {
                    parametro = parametro + getParametrosValue().get(i);
                }
                ejecucion = ejecucion + parametro + " ,";
            }
            ejecucion = ejecucion.substring(0, ejecucion.length() - 1);
            logger.info("------------------------------");
            logger.info("usuario: " + usuario);
            logger.info(consulta + " " + ejecucion);
        } catch (Exception e) {
            logger.error("ErrorLOG:", e);
        } finally {
            if (exception != null) {
                logger.error("Error:", exception);
            }
            PARAMETROS = null;
            parametrosName = null;
            parametrosValue = null;
            parametrosType = null;
        }
    }

    public List<String> getParametrosName() {
        if (parametrosName == null) {
            parametrosName = new ArrayList<>();
        }
        return parametrosName;
    }

    public void setParametrosName(List<String> parametrosName) {
        this.parametrosName = parametrosName;
    }

    public List<String> getParametrosValue() {
        if (parametrosValue == null) {
            parametrosValue = new ArrayList<>();
        }
        return parametrosValue;
    }

    public void setParametrosValue(List<String> parametrosValue) {
        this.parametrosValue = parametrosValue;
    }

    public List<Type> getParametrosType() {
        if (parametrosType == null) {
            parametrosType = new ArrayList<>();
        }
        return parametrosType;
    }

    public void setParametrosType(List<Type> parametrosType) {
        this.parametrosType = parametrosType;
    }

    public List<String> getParametros() {
        if (parametros == null) {
            parametros = new ArrayList<>();
        }
        return parametros;
    }

    

    public List select(String sql, Class target) {
        List lista;
        try {
            iniciarConsulta();
            Query selectquery = getNamedQuery(sql);
            lista = selectquery.setResultTransformer(Transformers.aliasToBean(target)).list();
        } catch (Exception e) {
            UtilException.error(e, logger);
            lista = null;
        } finally {
            cerrarSession();
        }
        return lista;
    }

    public List select(String sql, List<Param> Params, Class target) {
        List lista;
        try {
            iniciarConsulta();
            Query selectquery = getNamedQuery(sql);
            if (Params != null) {
                for (Param param : Params) {
                    selectquery = selectquery.setParameter(param.getString(), param.getO());
                }
            }
            lista = selectquery.setResultTransformer(Transformers.aliasToBean(target)).list();
        } catch (Exception e) {
            UtilException.error(e, logger);
            lista = null;
        } finally {
            cerrarSession();
        }
        return lista;
    }

    public Object find(String sql, Object id, Class target) {
        Object objeto;
        try {
            iniciarConsulta();
            objeto = getNamedQuery(sql)
                    .setParameter("id", id)
                    .setResultTransformer(Transformers.aliasToBean(target)).uniqueResult();
        } catch (Exception e) {
            UtilException.error(e, logger);
            objeto = null;
        } finally {
            cerrarSession();
        }
        return objeto;
    }

    public Object find(String sql, List<Param> Params, Class target) {
        Object objeto;
        try {
            iniciarConsulta();
            Query selectquery = getNamedQuery(sql);
            for (Param param : Params) {
                selectquery = selectquery.setParameter(param.getString(), param.getO());
            }
            objeto = selectquery.setResultTransformer(Transformers.aliasToBean(target)).uniqueResult();
        } catch (Exception e) {
            UtilException.error(e, logger);
            objeto = null;
        } finally {
            cerrarSession();
        }
        return objeto;
    }

    public Query getNamedQuery(String sql) {
        return session.getNamedQuery(sql);
    }
}
