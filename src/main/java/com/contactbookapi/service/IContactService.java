package com.contactbookapi.service;

import java.util.List;

/**
 * The interface Contact service.
 *
 * @param <L> the type parameter
 * @param <T> the type parameter
 */
public interface IContactService<L, T> {
    /**
     * Create l.
     *
     * @param l the l
     * @return the l
     */
    public L create(L l);

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<L> getAll();

    /**
     * Gets by id.
     *
     * @param t the t
     * @return the by id
     */
    public L getById(T t);

    /**
     * Update l.
     *
     * @param l the l
     * @return the l
     */
    public L update(L l);

    /**
     * Delete by id.
     *
     * @param t the t
     */
    public void deleteById(T t);
}
