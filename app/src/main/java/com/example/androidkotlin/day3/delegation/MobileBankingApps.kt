package com.example.androidkotlin.day3.delegation

class MobileBankingApps(private var cekSaldo: CekSaldo
, private var transfer: Transfer): CekSaldo by cekSaldo, Transfer by transfer