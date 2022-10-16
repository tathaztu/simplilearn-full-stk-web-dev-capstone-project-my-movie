export class User {
    public strUserId: string = '';
    public strFirstName: string = '';
    public strMiddleName: string = '';
    public strLastName: string = '';
    public strEMail: string = '';
    public strPassword: string = '';
    public bAdminUser: boolean = false;

    constructor() {
        console.log("Printing User Object - " + JSON.stringify(this)); 
    }


}
