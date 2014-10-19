/**
 * 
 */
var state_arr=new Array("California","Masachussets","New York","Texas");
var city_arr=new Array();
city_arr[0]="";
city_arr[1]="Los Angeles|San Jose|San Diego|San Francisco";
city_arr[2]="Boston|Berkshire|Worcester|Salem";
city_arr[3]="Buffalo|New York|Rochester|Syracuse";
city_arr[4]="Arlington|Austin|Dallas|San Antonio";

function print_state(state_id){
	var option_str = document.getElementById(state_id);
	option_str.length=0;
	option_str.options[0] = new Option('Select State','');
	option_str.selectedIndex = 0;
	for (var i=0; i<state_arr.length; i++) {
		option_str.options[option_str.length] = new Option(state_arr[i],state_arr[i]);

}
}

function print_city(city_id, city_index){
	var option_str = document.getElementById(city_id);
	option_str.length=0;	// Fixed by Julian Woods
	option_str.options[0] = new Option('Select City','');
	option_str.selectedIndex = 0;
	var city_array = city_arr[city_index].split("|");
	for (var i=0; i<state_arr.length; i++) {
		option_str.options[option_str.length] = new Option(city_array[i],city_array[i]);
	}
}
