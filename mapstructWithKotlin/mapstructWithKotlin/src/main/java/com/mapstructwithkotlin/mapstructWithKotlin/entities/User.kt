package com.mapstructwithkotlin.mapstructWithKotlin.entities;

import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    var id: Int,
    var username: String,
    var useremail: String,
    var age: Int
)
{}
