package com.example.generated_embeded_key;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Stream;

public class MyGenerator
  implements IdentifierGenerator, Configurable {

    private String prefix;

    @Override
    public Serializable generate(
            SharedSessionContractImplementor session, Object obj)
      throws HibernateException {

        EntityPersister entityPersister = session.getEntityPersister(obj.getClass().getName(), obj);

        String query = String.format("select %s from %s",
            entityPersister.getIdentifierPropertyName(),
            obj.getClass().getSimpleName());

        Stream<CompanyPk> ids = session.createQuery(query, CompanyPk.class).stream();

        Long max = ids.map(pk -> pk.getId()).map(o -> o.replace(prefix + "-", ""))
          .mapToLong(Long::parseLong)
          .max()
          .orElse(0L);

        return new CompanyPk(prefix + "-" + (max + 1));
    }

    @Override
    public void configure(Type type, Properties properties,
                          ServiceRegistry serviceRegistry) throws MappingException {
        prefix = properties.getProperty("prefix");
    }
}
