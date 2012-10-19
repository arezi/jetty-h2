# jetty-h2

Life cycle H2 database control to Jetty server.

This Jetty "plugin" was made for H2 database up (opening tcp port to connections) and down with Jetty server. It also starts the H2 webconsole if you want.

# Usage

Put the jetty-h2-x.x.x.jar and h2-<version>.jar in jetty-<version>/lib

Put the jetty-h2.xml in jetty-<version>/etc

Edit jetty-h2.xml if you wish.

Include the jetty-h2.xml in your start.ini or in your command line startup. 
