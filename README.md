# README

This weather app is made by Group 18 of the Interaction Design course, Computer Science Tripos 2016-17, at the University of Cambridge.
It is intended for Parachute Engineers, specifically the father of a member of the team.

The app consists of two parts: the map, and the tabs.
The map shows the forecast of the next 36 hours, in desired map selected by the checkboxes on the left side of the screen.
The forecast can be changed by the slider on the bottom of the map, which brings in the new map data in 3 hour steps.
Precipitation, Pressure, Cloud, and Temperature maps are available, and are given by the public API of the Met Office.
There also is a zoom slider which adjusts the zoom level of the map.

On the right-hand side of the screen there is a collection of 
extendable and collapsible tabs, that provide data as text (numerical values).
Each tab corresponds to a day (for a total of 5 days, starting
from the current day). When collapsed, a tab only shows 3 pieces
of information - the essentials: wind, temperature and precipitation.
When expanded by a mouse click, a tab contains data displayed in three hour steps, starting with the
time interval containing the current time. Information is displayed
as compact icons, with their corresponding values next to them. The parameters
that are provided are: wind speed, wind direction, temperature,
precipitation, UV radiation and humidity.

