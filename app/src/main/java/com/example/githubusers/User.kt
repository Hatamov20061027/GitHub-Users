package com.example.githubusers

import com.squareup.picasso.RequestCreator

class User {
    var name:String?=null
    var img:String?=null

    constructor(name:String, img: String){
        this.name=name
        this.img=img
    }

    constructor(name: String?) {
        this.name = name
    }
}