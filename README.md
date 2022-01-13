# StatusIQ-Android-SDK
Accessing services from anywhere at any time has been made possible with mobile applications. With businesses needing to ensure uninterrupted availability, it is crucial to keep customers aware of the status of services through mobile applications that provide transparency as well as ease of access.
In this context, a public status page like StatusIQ can help you post real-time updates to keep your customers informed about your active incidents or scheduled maintenance. It also lets you publish postmortem reports and do much more. You can integrate your mobile applications with StatusIQ to help users track service status updates.
The StatusIQ Android SDK is a free module for StatusIQ. The perk is that you can integrate the StatusIQ Android SDK with your company's mobile app and showcase your real-time service availability to clients. The integration also provides various customization options, like changing the font and colors.

Steps to integrate the StatusIQ Android SDK into your business app

1. Import the StatusIQ Android SDK into your business app by cloning the source code or by including the StatusIQ SDK.aar.

2. After importing the SDK, it is important to specify the Status_page_url under defaultConfig in your app-level build.gradle.

<img width="637" alt="Screenshot 2022-01-13 at 2 09 52 PM" src="https://user-images.githubusercontent.com/97294110/149295844-ca3fe496-3041-41ef-887b-2b5a2c35b9d1.png">

3. If you wish to show only the status of a specific component, then add two more  values, Show_Component_status_alone and Component_Name, as shown below:

<img width="622" alt="Screenshot 2022-01-13 at 2 08 59 PM" src="https://user-images.githubusercontent.com/97294110/149295962-8183c3e9-836a-427e-ba1d-09152579bd0c.png">

4. After entering these details, rebuild your project and use the StatusIq.openStatusIqActivity() method to display the status screen. The first parameter is the context, and the second parameter is the title you wish to display on the action bar.

<img width="706" alt="Screenshot 2022-01-13 at 2 11 13 PM" src="https://user-images.githubusercontent.com/97294110/149296056-efbaad35-94e3-460c-9389-7d6bfb7a25e0.png">

**How to customize the StatusIQ Framework**

You can customize the user interface for the StatusIQ Framework to suit your application theme. 

To customize, use the StatusIq.setTheme() method before using the StatusIq.openStatusIqActivity() method. You can customize various aspects, like font, text color, and background color, by passing the custom theme.

<img width="720" alt="Screenshot 2022-01-13 at 2 11 40 PM" src="https://user-images.githubusercontent.com/97294110/149297302-608cc1ff-58d5-478d-936a-59f30d1ca634.png">


<img width="790" alt="Screenshot 2022-01-13 at 2 23 16 PM" src="https://user-images.githubusercontent.com/97294110/149297522-ac96c38d-4854-4df7-8356-488c324ada73.png">


![Screenshot_20211227-123138_Status IQ SDK](https://user-images.githubusercontent.com/97294110/149298835-e47b437f-b9c9-4b45-bf68-3fbd45848ba8.jpg)


![i01_Screenshot_20220106-154356_Status IQ SDK](https://user-images.githubusercontent.com/97294110/149298880-41974769-5c43-43ca-a770-e860633020c2.jpg)


![i01_Screenshot_20220106-154435_Status IQ SDK](https://user-images.githubusercontent.com/97294110/149298902-42f384f0-bc04-4626-908f-c0c22ac22999.jpg)


![i01_Screenshot_20220106-161328_Status IQ SDK](https://user-images.githubusercontent.com/97294110/149298928-6aa9a89d-9846-4b8d-b978-2fc12808a526.jpg)




