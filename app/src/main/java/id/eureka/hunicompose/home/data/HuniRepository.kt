package id.eureka.hunicompose.home.data

import id.eureka.hunicompose.home.presentation.model.Huni
import id.eureka.hunicompose.home.presentation.model.HuniFilterSpec

interface HuniRepository {
    fun getHuni(filterSpec: HuniFilterSpec) : List<Huni>
    fun getHuniDetail() : Huni
}