package com.example.livestreaming.fragments.account

data class LoginOutput(
    val fullName: String?,
    val isactive: String?,
    val msisdn: String?,
    val orgID: String?,
    val profilephotoID: String?,
    val userId: String?,
    val userName: String?,
    val verified: Boolean?,
    val device: Boolean?
)