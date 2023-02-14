package com.mapstructwithkotlin.mapstructWithKotlin.entities

data class UserDto(
    var id: Int,
    var dtoName: String,
    var dtoEmail: String,
    var age: Int,
    var password: String ?,
    var description : String?
)
{
}