package org.tracker.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.common.repository.NameIdRepository;

@Service
public class NameIdServiceImpl implements NameIdService {

    private final NameIdRepository nameIdRepository;

    @Autowired
    public NameIdServiceImpl(NameIdRepository nameIdRepository) {
        this.nameIdRepository = nameIdRepository;
    }

    @Override
    public Long findIdByName(String tableName, String name) {
        return nameIdRepository.findIdByName(tableName, name);
    }
}