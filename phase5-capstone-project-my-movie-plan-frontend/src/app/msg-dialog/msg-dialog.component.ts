import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-msg-dialog',
  templateUrl: './msg-dialog.component.html',
  styleUrls: ['./msg-dialog.component.css']
})
export class MsgDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: {strMsgType: String, strMsg: String}) {
    console.log(data.strMsgType + ' | ' + data.strMsg)
  }

  ngOnInit(): void {
  }

}
