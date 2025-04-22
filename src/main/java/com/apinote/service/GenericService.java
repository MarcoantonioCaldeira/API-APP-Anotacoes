package com.apinote.service;

import java.util.List;

public interface GenericService<O, I, ID> {

    O save(I entity);
    O update(ID id, I entity);
    void delete(ID id);
    O read(ID id);
}
