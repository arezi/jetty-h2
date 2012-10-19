# jetty-h2

H2 database Lifecycle control for Jetty web server 

This Jetty "plugin" was made for H2 database up (opening tcp port to connections) and down with Jetty server. It also starts the H2 webconsole if you want.

# Usage

Put the jetty-h2-x.x.x.jar and h2-&lt;version&gt;.jar in jetty-&lt;version&gt;/lib

Put the jetty-h2.xml in jetty-&lt;version&gt;/etc directory and edit it if you wish.

Include the jetty-h2.xml in your start.ini or in your command line startup. 
