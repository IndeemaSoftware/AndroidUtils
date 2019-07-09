![LOGO](https://github.com/IndeemaSoftware/EEAndroidRealmBrowser/blob/assets/indeema_logo.jpg?raw=true)
# AndroidUtils

Android Utilities for Android OS developed by [Indeema Software Inc.](https://indeema.com/). It provides ability to use default utilities.

## Requirements

- Realm gradle plugin version 4.X.X


## Integration

## Add this into project build.gradle file:
```
allprojects {
    repositories {
        jcenter()

        // Add these line
        maven { url "http://maven.indeema.com:8081/artifactory/libs-release" }
    }
}
```

## Add this into application build.gradle file:
```
dependencies {
    implementation 'com.indeema.libs:androidutils:1.0.0'
}
```

# Boost Version History

## Version 1.0.0

Set of most common and useful android utilities:

- AnimationUtils
- ColorUtils
- ConnectivityUtils
- DialogUtils
- DisplayUtils
- EncryptionUtils
- FragmentUtils
- MathUtils
- ViewUtils

## Communication and Support
If you encounter an issue or you have any comments or propositions with using **AndroidUtils** then you can reach us at **support@indeema.com**.

- You can also follow our news at [@IndeemaSoftware](https://twitter.com/IndeemaSoftware) or on our [blog](https://indeema.com/blog).

## License
**AndroidUtils** works under the MIT license. For more information see [here](https://github.com/IndeemaSoftware/EEAndroidRealmBrowser/blob/master/LICENSE).

To know more about us and our [Mobile expertise](https://indeema.com/services/mobiledevelopment), visit our website https://indeema.com
