/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function myFunction(index) {
    
    
    //var elementsLI = document.getElementsByTagName('li');
    var elementsLI = $('#idMenu li');;
    elementsLI[index].addClass('active');
    //$('#idMenu li:not(.active)');
    
    
//    for(i=0;i<8;i++){
//        if(index!==i){
//           elementsLI[index].removeClass( "active" ); 
//        }
//    }
    
}