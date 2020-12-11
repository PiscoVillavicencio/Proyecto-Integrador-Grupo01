package com.commerce;

 
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Ignore
public class CompanyRepositoryTest {
 
    @Test
    public void testFindByName() {
        System.out.println("GtestFindByName");
        
        
        //row.setCreator(1);
        //ReturnApp out=typeCompanyService.insert(row);
        /*if(out.OUT_MESSAJE_VAL== MessageResult.OUT_SUCCESS){
            System.out.println("Guardado correctamente");
            for (TypeCompanyEntity r : typeCompanyService.select(new TypeCompanyEntity())){
                System.out.println("type::"+r.getAppellation());
            }
        }else{
            System.out.println("Error al guardar");
        }
*/
    }


    /*
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository repository;

    @Test
    public void testFindByName() {

        entityManager.persist(new Book("C++"));

        List<Book> books = repository.findByName("C++");
        assertEquals(1, books.size());

        assertThat(books).extracting(Book::getName).containsOnly("C++");

    }*/

}
