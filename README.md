# StatusIQ-Android-SDK
Accessing services from anywhere at any time has been made possible with mobile applications. With businesses needing to ensure uninterrupted availability, it is crucial to keep customers aware of the status of services through mobile applications that provide transparency as well as ease of access.
In this context, a public status page like StatusIQ can help you post real-time updates to keep your customers informed about your active incidents or scheduled maintenance. It also lets you publish postmortem reports and do much more. You can integrate your mobile applications with StatusIQ to help users track service status updates.
The StatusIQ Android SDK is a free module for StatusIQ. The perk is that you can integrate the StatusIQ Android SDK with your company's mobile app and showcase your real-time service availability to clients. The integration also provides various customization options, like changing the font and colors.

## Steps to integrate the StatusIQ Android SDK into your business app

1. Import the StatusIQ Android SDK into your business app by cloning the source code or by including the StatusIQ SDK.aar.

   **Note: If you are importing the SDK as a .arr file, ensure that your app level build.gradle contains the following dependencies**

```groovy


       implementation 'com.squareup.retrofit2:retrofit:2.9.0'
       implementation 'com.squareup.retrofit2:converter-scalars:2.3.0'
       implementation 'androidx.recyclerview:recyclerview:1.2.1'
       implementation "androidx.cardview:cardview:1.0.0"
       implementation 'androidx.constraintlayout:constraintlayout:2.1.2'
       implementation 'androidx.appcompat:appcompat:1.3.1'

```

2. After importing the SDK, it is important to specify the Status_page_url under defaultConfig in your app-level build.gradle.

```groovy


        defaultConfig {

        applicationId "com.zoho.statusiqsdkapp"
        minSdkVersion 19
        targetSdkVersion 30
        versionCode 1
        versionName "1.0"

        resValue "string", "Status_page_url", "https://status.site24x7.com/"

       }

```

3. If you wish to show only the status of a specific component, then add two more  values, Show_Component_status_alone and Component_Name, as shown below:

```groovy


        defaultConfig {

        applicationId "com.zoho.statusiqsdkapp"
        minSdkVersion 19
        targetSdkVersion 30
        versionCode 1
        versionName "1.0"

        resValue "string", "Status_page_url", "https://status.site24x7.com/"
        resValue "string", "Show_Component_status_alone", "1"
        resValue "string", "Component_Name","Site24x7 StatusIQ"
  
       }

```

4. After entering these details, rebuild your project and use the StatusIq.openStatusIqActivity() method to display the status screen. The first parameter is the context, and the second parameter is the title you wish to display on the action bar.

```kotlin

      StatusIq.openStatusIqActivity(this@MainActivity, "Service status")

```

## How to customize the StatusIQ Framework

You can customize the user interface for the StatusIQ Framework to suit your application theme. 

To customize, use the StatusIq.setTheme() method before using the StatusIq.openStatusIqActivity() method. You can customize various aspects, like font, text color, and background color, by passing the custom theme.


```kotlin

      StatusIq.setTheme(R.style.Theme_StatusIQSDK)
      StatusIq.openStatusIqActivity(this@MainActivity, "Service status")

```

```xml

     <style name="Theme.StatusIQSDK" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
       
        <!-- Customize your theme here. -->

        <!-- Primary color. -->
        <item name="colorPrimary">@color/purple_500</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorOnPrimary">@color/white</item>
        <!-- Secondary color. -->
        <item name="colorSecondary">@color/teal_200</item>
        <item name="colorSecondaryVariant">@color/teal_700</item>
        <item name="colorOnSecondary">@color/black</item>
        <item name="textAllCaps">true</item>
        <!-- Status bar color. -->
        <item name="android:statusBarColor" tools:targetApi="l">?attr/colorPrimaryVariant</item>

       
     </style>

```


<br>

<p float="left">
<img width="300" alt="Screenshot 2022-01-13 at 2 23 16 PM" src="https://user-images.githubusercontent.com/97294110/149318278-803dedb8-72e7-42ce-9f6b-1a4e8f445ffd.png"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<img width="300" alt="Screenshot 2022-01-13 at 2 23 16 PM" src="https://user-images.githubusercontent.com/97294110/149318468-8381c648-e5fd-4d5f-8b88-b1cf9d497dad.png">
</p>

<br>

<p float="left">
<img width="300" alt="Screenshot 2022-01-13 at 2 23 16 PM" src="https://user-images.githubusercontent.com/97294110/149318807-aa6b6662-2c63-46a2-95ee-4e6e3016548d.png"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<img width="300" alt="Screenshot 2022-01-13 at 2 23 16 PM" src="https://user-images.githubusercontent.com/97294110/149318897-6c272cba-c87f-4d69-a8ec-97af51290079.png">
</p>



