# PermissionsReference
The purpose of this project is to create an exhaustive list of methods that developers have to take into account while implementing [Runtime Permissions] (https://developer.android.com/training/permissions/requesting.html) in Android Marshmallow and higher.

# The Idea of Permissions Reference
I encourage every Android developer to contribute to this project and this way we will have an exhaustive list of methods that we have to handle if we are implementing Runtime Permissions.
I've added some of them. Adding all of them is a bit mundane job, so probably I won't contribute until I know it's in demand.

| Permissions Group                      | Permission                         | Method that requires permission  |
| :------------------------------------- |:---------------------------------- |:--------------------------------:|
| android.permission-group.CALENDAR      |                                    |                                  |
|                                        | android.permission.READ_CALENDAR   |                                  |
|                                        |                                    |[ContentResover.query()] (https://developer.android.com/reference/android/content/ContentResolver.html#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String))|
|                                        |                                    |[ContentResolver.canonocalize()] (https://developer.android.com/reference/android/content/ContentResolver.html#canonicalize(android.net.Uri))|
|                                        |                                    |[ContentResolver.uncanonocalize()] (https://developer.android.com/reference/android/content/ContentResolver.html#uncanonicalize(android.net.Uri))|
|                                        |android.permission.WRITE_CALENDAR   |
|                                        |                                    |[ContentResolver.insert()] (https://developer.android.com/reference/android/content/ContentResolver.html#insert(android.net.Uri, android.content.ContentValues))
|                                        |                                    |[ContentResolver.bulkInsert()] (https://developer.android.com/reference/android/content/ContentResolver.html#bulkInsert(android.net.Uri, android.content.ContentValues[]))
|                                        |                                    |[ContentResolver.update()] (https://developer.android.com/reference/android/content/ContentResolver.html#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]))
|                                        |                                   |[ContentResolver.applyBatch()] (https://developer.android.com/reference/android/content/ContentResolver.html#applyBatch(java.lang.String, java.util.ArrayList<android.content.ContentProviderOperation>))
|                                        |                                   |[ContenResolver.delete()] (https://developer.android.com/reference/android/content/ContentResolver.html#delete(android.net.Uri, java.lang.String, java.lang.String[]))
|                                        |                                   | The same principle works if we use Cursor Loader. Demo with Cursor Loader is created using Contacts Permission (not Calendar)
|android.permission-group.CAMERA         |                                   |
|                                        |android.permission.CAMERA          |[Cameara.open()] (https://developer.android.com/reference/android/hardware/Camera.html#open(int))
|                                        |                                   |[CameraManager.openCamera()] (https://developer.android.com/reference/android/hardware/camera2/CameraManager.html#openCamera(java.lang.String, android.hardware.camera2.CameraDevice.StateCallback, android.os.Handler))
|android.permission-group.CONTACTS       |                                   |
|                                        |android.permission.READ_CONTACTS   |
|                                        |                                   |[ContentResover.query()] (https://developer.android.com/reference/android/content/ContentResolver.html#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String))
|                                        |                                   |[LoaderManager.initLoader()] (https://developer.android.com/reference/android/app/LoaderManager.html#initLoader(int, android.os.Bundle, android.app.LoaderManager.LoaderCallbacks<D>))
|                                        |TODO: here we should put all that left |

# Sample Application
The application was created to show how exactly we call each specific method.
You can choose two options:
- Crash. The applications will crash with Security exception when calling method that require permission.
- Ask for permission. The application will gently request needed permission.

![permissions_reference_screen_shot](https://cloud.githubusercontent.com/assets/2686355/15980251/e4553d86-2f72-11e6-85e4-ef60e3bcf6cc.png)

# The Origin
We've recently had to implement support of Runtime Permission in my current project. Project is big (200K lines of Java code), and looking for every place where we need to implement requesting runtime permissions became real pain. Some of the places can be found by lint, but not all of them. For example using camera requires permisison but lin doesn't show it.

# License

Copyright 2016 Danylo Volokh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
