/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function ($) {
    $(function () {

        $('.button-collapse').sideNav();

    }); // end of document ready
})(jQuery); // end of jQuery name space


///SIDEBAR
// Initialize collapse button
$('.button-collapse').sideNav({
    menuWidth: 300, // Default is 300
    edge: 'left', // Choose the horizontal origin
    closeOnClick: true, // Closes side-nav on <a> clicks, useful for Angular/Meteor
    draggable: true, // Choose whether you can drag to open on touch screens,
    onOpen: function (el) { /* Do Stuff */
    }, // A function to be called when sideNav is opened
    onClose: function (el) { /* Do Stuff*/
    }, // A function to be called when sideNav is closed
}
);
// Initialize collapsible (uncomment the line below if you use the dropdown variation)
//$('.collapsible').collapsible();