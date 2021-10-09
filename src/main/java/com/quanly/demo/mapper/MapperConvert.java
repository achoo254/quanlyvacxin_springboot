package com.quanly.demo.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperConvert {
    public <S, T> List<T> mapList(List<S> listSource, Class<T> target) {
        ModelMapper modelMapper = new ModelMapper();
        return listSource.stream().map(source -> modelMapper.map(source, target)).collect(Collectors.toList());
    }
}
