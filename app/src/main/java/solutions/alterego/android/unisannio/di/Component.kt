package solutions.alterego.android.unisannio.di

import solutions.alterego.android.unisannio.App
import solutions.alterego.android.unisannio.HomeActivity
import javax.inject.Singleton

@Singleton
@dagger.Component(modules = arrayOf(SystemServicesModule::class))
interface Component {

    fun inject(activity: HomeActivity)

    object Initializer {
        fun init(app: App): Component {
            return DaggerComponent.builder().systemServicesModule(SystemServicesModule(app)).build()
        }
    }
}
