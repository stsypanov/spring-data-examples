package com.luxoft.logeek;

import com.luxoft.logeek.repository.ChildRepository;
import com.luxoft.logeek.repository.ParentRepository;
import com.luxoft.logeek.repository.PupilRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Transactional
@ActiveProfiles(profiles = "h2")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AppConfig.class)
public abstract class TestBase {
  @PersistenceContext
  protected EntityManager em;
  @Autowired
  protected PupilRepository pupilRepository;
  @Autowired
  protected ChildRepository childRepository;
  @Autowired
  protected ParentRepository parentRepository;

  protected Random random;

  void setUp() {
    initRandom();
  }

  protected void initRandom() {
    random = ThreadLocalRandom.current();
  }

  @BeforeTransaction
  void beforeTransaction() {
    System.out.println("------------------");
    System.out.println("Transaction begins");
  }

  @AfterTransaction
  public void afterTransaction() {
    System.out.println("------------------");
    System.out.println("Transaction finished");
  }
}
