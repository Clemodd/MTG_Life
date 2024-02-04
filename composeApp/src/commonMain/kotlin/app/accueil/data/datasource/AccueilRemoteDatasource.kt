package app.accueil.data.datasource

import app.accueil.data.api.AccueilApi

interface AccueilRemoteDataSource {
}

class AccueilRemoteDataSourceImpl(private val api: AccueilApi) : AccueilRemoteDataSource {
}
