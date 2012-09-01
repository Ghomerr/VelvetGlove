[VELVET GLOVE]

This plugin allows you to deal no damage to players and/or other entities by having the selected "velvet glove" in hand when attacking.

There is no configuration to do. 

The main command is: /velvetglove (with these aliases: /vglove, /vg).

You can do this if you have the permission "velvetglove.admin":
/vg ON : enable the velvet glove for everyone who has the permission "velvetglove.user".
/vg OFF : disable the velvet glove.
/vg SET <material> : define the velvet glove (default is SNOW_BALL) (*)
/vg TARGETS [PLAYERS|OTHERS] [true|false] : select the targets of the velvet glove (default true for both) (*)
/vg : display the help message

(*) : The configuration will be updated with the new value

The plugin uses only Bukkit internal permissions system.

Permissions:
velvetglove.admin # To administrate the plugin
velvetglove.user # To use the velvet glove