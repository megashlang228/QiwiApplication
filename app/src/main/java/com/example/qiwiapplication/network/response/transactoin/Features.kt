package com.example.qiwiapplication.network.response.transactoin

data class Features(
    val bankDocumentAvailable: Boolean,
    val bankDocumentReady: Boolean,
    val chatAvailable: Boolean,
    val chequeReady: Boolean,
    val favoritePaymentEnabled: Boolean,
    val greetingCardAttached: Boolean,
    val regularPaymentEnabled: Boolean,
    val repeatPaymentEnabled: Boolean
)