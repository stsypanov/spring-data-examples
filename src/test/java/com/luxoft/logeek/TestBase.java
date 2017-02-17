package com.luxoft.logeek;

import com.luxoft.logeek.entity.Pupil;
import com.luxoft.logeek.repository.PupilRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public abstract class TestBase {
    @PersistenceContext
    protected EntityManager em;

    @Autowired
    protected PupilRepository repository;

    @Before
    public void setUp() throws Exception {
        List<Pupil> pupils = Arrays.asList(
                new Pupil(1),
                new Pupil(2),
                new Pupil(3),
                new Pupil(4),
                new Pupil(5),
                new Pupil(6)
        );
        repository.save(pupils);
        repository.flush();
        em.clear();
    }

}
