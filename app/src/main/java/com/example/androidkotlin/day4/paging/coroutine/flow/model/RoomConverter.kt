package com.example.androidkotlin.day4.paging.coroutine.flow.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.room.ProvidedTypeConverter

@ProvidedTypeConverter
class RoomConverter {

    @TypeConverter
    fun companyToJsonString(company: Company?) : String?{
        return Gson().toJson(company)
    }

    @TypeConverter
    fun jsonStringToCompany(company: String?): Company?{
        return Gson().fromJson(company, Company::class.java)
    }

    @TypeConverter
    fun companiesToJsonString(companies: List<Company>?) : String?{
        return Gson().toJson(companies)
    }

    @TypeConverter
    fun jsonStringToCompanies(companies: String?): List<Company>?{
        return Gson().fromJson(companies, object : TypeToken<List<Company>>() {}.type)
    }

    @TypeConverter
    fun addressToJsonString(address: Address?) : String?{
        return Gson().toJson(address)
    }

    @TypeConverter
    fun jsonStringToAddress(address: String?): Address?{
        return Gson().fromJson(address, Address::class.java)
    }

    @TypeConverter
    fun addressesToJsonString(addresses: List<Address>?) : String?{
        return Gson().toJson(addresses)
    }

    @TypeConverter
    fun jsonStringToAddresses(addresses: String?): List<Address>?{
        return Gson().fromJson(addresses, object : TypeToken<List<Address>>() {}.type)
    }

    @TypeConverter
    fun goeToJsonString(geo: Geo?) : String?{
        return Gson().toJson(geo)
    }

    @TypeConverter
    fun jsonStringToGeo(geo: String?): Geo?{
        return Gson().fromJson(geo, Geo::class.java)
    }

    @TypeConverter
    fun geosToJsonString(geos: List<Geo>?) : String?{
        return Gson().toJson(geos)
    }

    @TypeConverter
    fun jsonStringToGeos(geos: String?): List<Geo>?{
        return Gson().fromJson(geos, object : TypeToken<List<Geo>>() {}.type)
    }
}