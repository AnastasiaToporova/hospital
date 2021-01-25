package app.repository;

import java.util.Collection;

public interface IBookingRepository<T, I> {

    void save(T booking);

    void saveWithSimpleJdbcInsert(T booking);

    Collection<T> findAll();

    T findOne(I id);

    void update(T booking);

    void batchUpdateUsingNamedParameterJDBCTemplate(final Collection<T> bookings);

}
