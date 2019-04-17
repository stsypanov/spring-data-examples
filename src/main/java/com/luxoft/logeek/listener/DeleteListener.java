package com.luxoft.logeek.listener;

import org.hibernate.event.internal.DefaultDeleteEventListener;
import org.hibernate.event.spi.DeleteEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by Сергей on 11.04.2017.
 */
@Component
@Profile("deleteListenerOn")
public class DeleteListener
  extends DefaultDeleteEventListener {

  @Override
  public void onDelete(DeleteEvent event) {
    String entityName = event.getEntityName();
    Object object = event.getObject();

    //todo implement some logic here
    super.onDelete(event);
  }
}
