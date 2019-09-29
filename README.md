# parking-lot
=======

####Description:
This is a ticketing system for a parking lot. When a car enters parking lot, the system issued a ticket to the driver. 
The ticket issuing process includes documenting the registration number (number plate) and 
the colour of the car and allocating an available parking slot to the car 
At the exit the customer returns the ticket which then marks the slot they were using as being available.

<strong>The system support below list of commands:</strong>
- <strong>create_parking_lot:</strong> create parking slot. `eg. create_parking_lot 6`
- <strong>park:</strong> park car of colour with registration number. `eg. park reg-num-1234 White`
- <strong>leave:</strong> leave a parking lot. `eg. leave 6` means leave slot 6
- <strong>status:</strong> print the current parking lot registration status
- <strong>registration_numbers_for_cars_with_colour:</strong> get registration number of cars with colour. `eg. registration_numbers_for_cars_with_colour White`
- <strong>slot_numbers_for_cars_with_colour:</strong> get slot numbers of car with colour. `eg. slot_numbers_for_cars_with_colour White`
- <strong>slot_number_for_registration_number:</strong> get slot number registered with registration number. `eg. slot_number_for_registration_number reg-num-111`


--------
The program is written in Java 8 with maven 3.
Below are the scripts to build and run the code
### Scripts:

`bin/setup`: run maven install. a target folder with runnable jar will be created<br>
`bin/parking_lot`: run the program. arg: input file path. If no argument, read from standard input.