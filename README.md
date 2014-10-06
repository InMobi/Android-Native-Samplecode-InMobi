Android-Native-Code
===================

This project has all the required classes for native - native request(API), handling impression/click, opening LP.



Classes under package “handleimpclick” => Fires impression/click javascript method in the contextCode, maintaining a global queue object.
 -> Publisher can specify internal UIWebViews, by modifying IM_MAX_WEBVIEW, depending upon the no. of native slots the application has.
 For maintaining low memory footprints, a max of 3-5 WebViews would be recommended if in case you have too many slots.
 You may increase this number to improve latency of firing impression/click beacons.
 -> Background tasks are handled
 
 * TODO *
 -> Handling impression/click timeouts, and failure cases
 -> Implement a max retry logic.
 -> Caching of native ad data