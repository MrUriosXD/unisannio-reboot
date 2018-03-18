package solutions.alterego.android.unisannio.navigation

import android.app.Activity
import android.support.v4.app.TaskStackBuilder

class Navigator(val activityContext: Activity) {

    fun upToParent() {
        val intent = activityContext.parentActivityIntent
        if (intent == null) {
            activityContext.finish()
            return
        }

        if (activityContext.shouldUpRecreateTask(intent)) {
            TaskStackBuilder.create(activityContext)
                    .addParentStack(activityContext)
                    .startActivities()
        } else {
            activityContext.navigateUpTo(intent)
        }
    }
}
