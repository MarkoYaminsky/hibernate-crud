package com.yaminsky.bankspringhibernate.service;

import com.yaminsky.bankspringhibernate.dto.ContinentDto;

public interface IContinentService extends IGeneralService<ContinentDto, Integer>{
    ContinentDto parametrizedInsertIntoTable(ContinentDto continentDto);
}
