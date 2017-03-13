package com.luxoft.logeek;

import com.luxoft.logeek.entity.Child;
import com.luxoft.logeek.entity.Parent;
import com.luxoft.logeek.entity.Pupil;
import com.luxoft.logeek.repository.ChildRepository;
import com.luxoft.logeek.repository.PupilRepository;
import org.junit.After;
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
    protected PupilRepository pupilRepository;
    @Autowired
    protected ChildRepository childRepository;

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
        pupilRepository.save(pupils);
        pupilRepository.flush();

        Parent papa = new Parent("папа");
        Parent mama = new Parent("мама");

        Child child1 = new Child(papa);
        Child child2 = new Child(papa);
        Child child3 = new Child(papa);
        Child child4 = new Child(papa);

        Child child5 = new Child(mama);
        Child child6 = new Child(mama);

		childRepository.save(Arrays.asList(child1, child2, child3, child4, child5, child6));
		childRepository.flush();

        em.clear();
    }

	@After
	public void tearDown() throws Exception {
		pupilRepository.deleteAllInBatch();
		childRepository.deleteAllInBatch();
	}
}
