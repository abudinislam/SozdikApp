package ccn.zone.sozdik.base

import org.koin.core.module.Module

interface  InjectionModule {
    fun create(): Module
}