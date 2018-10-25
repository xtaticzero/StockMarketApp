/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function start() {
    statusDialog.show();
}

function stop() {
    statusDialog.hide();
}
function startFile() {
    PF('statusDialog').show();
}

function stopFile() {
    PF('statusDialog').hide();
}
function noEnter() {
    return event.keyCode != 13;
}