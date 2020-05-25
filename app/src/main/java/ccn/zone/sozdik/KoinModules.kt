package ccn.zone.sozdik

import ccn.zone.sozdik.translater.TransModule
import org.koin.core.module.Module

object KoinModules {
    val modules: List<Module> =
        listOf(
            TransModule.create()
            /*TranslaterModule.create()*/
        )

}