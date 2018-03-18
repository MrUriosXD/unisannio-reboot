package solutions.alterego.android.unisannio.di

import solutions.alterego.android.unisannio.App
import solutions.alterego.android.unisannio.GiurisprudenzaNewActivity
import javax.inject.Singleton

@Singleton
@dagger.Component(modules = arrayOf(SystemServicesModule::class))
interface Component {

    fun inject(activity: GiurisprudenzaNewActivity)

    object Initializer {
        fun init(app: App): Component {
            return DaggerComponent.builder().systemServicesModule(SystemServicesModule(app)).build()
        }
    }
}
