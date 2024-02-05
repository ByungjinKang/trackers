package org.tracker.common.convert;

import org.springframework.stereotype.Service;
import org.tracker.common.mapper.NameToIdMapper;

@Service
public class NameToId {
        private final NameToIdMapper nameToIdMapper;

        public NameToId(NameToIdMapper nameToIdMapper) {
            this.nameToIdMapper = nameToIdMapper;
        }

        public Long findIdByName(String tableName, String name) {
            return nameToIdMapper.findIdByName(tableName, name);
        }
}
