package com.mapstructwithkotlin.mapstructWithKotlin.mapper

import com.mapstructwithkotlin.mapstructWithKotlin.entities.Student
import com.mapstructwithkotlin.mapstructWithKotlin.entities.StudentEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface StudentMapper {
    fun getModelFromEntity(studentEntity: StudentEntity?): Student?

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "name", source = "name")



    fun getEntityFromModel(student: Student?): StudentEntity?
}