package com.migros.courier.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MapperService extends ModelMapper {


    public <T> T map(Object value, Class<T> target) {
        try {
            return super.map(value, target);
        } catch (Exception e) {
            log.error("When value converted to json, occurred an exception", e);
            return null;
        }
    }

    <S, T> List<T> mapList(List<S> source, Class<T> target) {
        return source
                .stream()
                .map(element -> super.map(element, target))
                .collect(Collectors.toList());
    }

}
