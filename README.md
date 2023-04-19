# Parking-Lot
My thirteenth JetBrains Academy project (graduate)

Description
Now it's time to add some query commands.

The command reg_by_color prints all registration numbers of cars of a particular color, taking color as a parameter. The color may be written in uppercase or lowercase letters. For example, reg_by_color BLACK. The answer should contain registration numbers separated by commas. The order should be the same as in the status command. If there are no cars of this color, the output should be like this: No cars with color BLACK were found..

The command spot_by_color is similar to the previous one, but it prints the parking space numbers of all the cars of a particular color.

The command spot_by_reg returns you the number of the spot where a car is located based on its registration number, for example, spot_by_reg KA-01. This command will also return an error message if your car was not found: No cars with registration number KA-01 were found. For convenience, let's suppose that all car registration numbers are unique.
