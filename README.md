![Gentle Reminders](https://i.imgur.com/7Gk5WGY.png)  

**Gentle Reminders** is a mod that sends mindful messages to the player every so often based on a customizable config file.  
Available for download on [Curseforge](https://www.curseforge.com/minecraft/mc-mods/gentlereminders) and [Modrinth](https://modrinth.com/mod/gentlereminders). Thank you for over 1800 downloads!   

## Features  
  
- Generates a config file on client startup if one doesn't exist
- Checks version of config file to guarantee latest message updates
- Client reads from config file to get settings
- On world join, sends an initial message if the mod is enabled
- Every X ticks, based on config, sends a mindful message
- When all messages are sent (in a random order with no repeats), resets the list and starts over
- New (as of v1.5.2) customizable toasts with block textures as the background and border!
- Config Guide below
- Commands Guide below

## Upcoming Features
- Add a drastically large amount of messages
  - Current # of simple messages: **34**
  - Current # of unique/special messages: **62**
- Add support for modded blocks to be used as the background/border
- Add custom message icons (not just an exclamation!)
- Make a cool GUI to edit custom toasts

## Message Examples  
  
**Simple Message 1**
![Simple Message 1](https://i.imgur.com/sKQtWIa.png)  
  
**Simple Message 2**
![Simple Message 2](https://i.imgur.com/0MAaaIN.png)  
  
**Unique Message 1**
![Unique Message 1](https://i.imgur.com/qx95uVV.png)

**Unique Message 2**
![Unique Message 2](https://i.imgur.com/sSVDy18.png)

**Custom Dark Oak Planks Background and Amethyst Block Border**
![Custom Message 1](https://i.imgur.com/6zmVxEp.png)

**Custom Blackstone Background and Resin Brick Border**
![Custom Message 2](https://i.imgur.com/T47B1gM.png)

**Custom Redstone Block Background and Diorite Border**
![Custom Message 3](https://i.imgur.com/nBFllTg.png)

**Custom Slime Block Background and Sand Border**
![Custom Message 4](https://i.imgur.com/fkixEBu.png)

**How it shows up in-game**
![In-Game Example](https://i.imgur.com/5GP3yGw.png)
  
## Config Guide  
  
The config file can be found at `config/gentlereminders-config.toml` (after running the game once), which can be edited with any text editor. You can change the values for the interval between messages, add or remove messages, and enable or disable the mod.   
  
Adding messages should be done in the following format:
```js
  {id=#, title="String", message="String", enabled=boolean, titleColor="String", messageColor="String"},
```
- `id` is a whole number that should be unique between messages  
- `title` is the title of the message that will be displayed at the top of the toast  
  - **Important Note**: This title can not include a comma (`,`) or the config file will break
- `message` is the message that will be displayed in the body of the toast 
  - **Important Note**: This message can not include a comma (`,`) or the config file will break
- `enabled` is a boolean that determines if the message can be sent - if you're setting to false anyway, you can also just remove the message unless you think you'll want to re-enable it later
- `titleColor` is a string that represents the color of the title text, which can be any of the colors below:
- `messageColor` is a string that represents the color of the message text, which can be any of the colors below:
```js
 "black", "dark_blue", "dark_green", "dark_aqua", "dark_red", "dark_purple", "gold", "gray", "dark_gray", "blue", "green", "aqua", "red", "light_purple", "yellow", "white"
```

## Command Guide
Required arguments are surrounded by `[brackets]`  

### `/gentlereminders` and `/gentlereminders help`
- Displays the help message

### `/gentlereminders get TimeRemaining`
- Displays the time remaining until the next message is sent

### `/gentlereminders get ConfigPath`
- Displays the path to the config file

### `/gentlereminders get ConfigVersion`
- Displays the version of the config file

### `/gentlereminders get DisplayStyle`
- Displays the current display style

### `/gentlereminders get TicksBetweenMessages`
- Displays the number of ticks between messages, as well as minutes and seconds

### `/gentlereminders get Messages [pageNumber]`
- Displays 5 messages at a time, starting at the page number specified

### `/gentleReminders set DisplayStyle [style]`
- Sets the display style to either `"default"`, `"light"`, `"dark"`, or `"chat"` (use the below command for `custom`)

### `/gentleReminders set CustomDisplayStyle [bgTexture] [borderTexture] [enableIcon]`
- Sets the display style to `custom`, then sets the background and border textures, as well as whether or not to include the exclamation mark

### `/gentlereminders set TicksBetweenMessages [ticks]`
- Sets the number of ticks between messages

### `/gentlereminders set Message [id] [title] [message] [enabled] [titleColor] [messageColor]`
- Overwrites the title, message, enabled status, and colors of a message by ID

### `/gentlereminders add Message [title] [message] [enabled] [titleColor] [messageColor]`
- Adds a new message with the specified title, message, enabled status, and colors

### `/gentlereminders remove Message [id]`
- Removes a message by ID

### `/gentlereminders enable GentleReminders`
- Enables the mod

### `/gentlereminders enable Message [id]`
- Enables a message by ID

### `/gentlereminders disable GentleReminders`
- Disables the mod

### `/gentlereminders disable Message [id]`
- Disables a message by ID
