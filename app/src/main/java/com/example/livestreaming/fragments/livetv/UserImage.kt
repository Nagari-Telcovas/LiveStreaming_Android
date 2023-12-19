package com.example.livestreaming.fragments.livetv

class UserImage {
    var name: String = ""
    var imageTv: Int = 0
    var videoUrl: String = ""

    constructor(name: String, imageTv: Int, videoUrl: String) {
        this.name = name
        this.imageTv = imageTv
        this.videoUrl = videoUrl
    }
}