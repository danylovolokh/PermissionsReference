# PermissionsReference
The purpose of this project is to create an exhaustive reference of methods that developers have to take into account while implementing Runtime Permissions in Android Marshmallow and higher.

# The Origin
We've recently had to implement support of Runtime permission in my current project. Project is big (200K lines of Java code), and looking for every place where we need to implement requesting runtime permissions become real pain. Some of the places can be found by lint, but not all of them. Sor example using camera requires permisison but lin doesn't show it.

# The Idea of Permissions Reference
I encourage every Andorid developer to contribute to this project and this way we will have an exhaustive list of methods that we have to handle if we are impleenting Runtime permissions.
I've added some of them. Adding all of them is a mundane job, so probably I won't continue before I know it's in demand.
