package com.example.myapplication.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

 class PermissionUtils {
  companion object {
      const val REQUEST_CODE_PERMISSION = 1
      var permissions = arrayOf(
          Manifest.permission.WRITE_EXTERNAL_STORAGE,
      )
      var alertDialog: AlertDialog? = null
      fun hasPermissions(context: Context?): Boolean {
          return (context?.let {
              ContextCompat.checkSelfPermission(
                  it,
                  Manifest.permission.READ_EXTERNAL_STORAGE
              )
              ContextCompat.checkSelfPermission(
                  it,
                  Manifest.permission.WRITE_EXTERNAL_STORAGE
              )
          }
                  == PackageManager.PERMISSION_GRANTED)
      }

      fun shouldShowRequestPermissionsRationale(activity: Activity): Boolean {
          if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
              return true
          }
          for (permission in permissions) {
              if (!activity.shouldShowRequestPermissionRationale(permission)) {
                  return false
              }
          }
          return true
      }

      fun showDialogRequestPermission(activity: Activity, cancelable: Boolean) {
          if (alertDialog == null) {
              alertDialog = AlertDialog.Builder(activity)
                  .setTitle("Request permission")
                  .setMessage("You have to give the camera permission and memory to use the app")
                  .setPositiveButton("Yes, Go to setting")
                  { dialog: DialogInterface, w: Int ->
                      val intent =
                          Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                      val uri =
                          Uri.fromParts("package", activity.packageName, null)
                      intent.data = uri
                      activity.startActivity(intent)
                      dialog.dismiss()
                  }
                  .create()
          }
          alertDialog!!.setCancelable(cancelable)
          alertDialog!!.show()
      }
  }
}
