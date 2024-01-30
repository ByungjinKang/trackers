package org.tracker.common.repository;

public interface NameIdRepository {
    Long findIdByName(String tableName, String name);
}