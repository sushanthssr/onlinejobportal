import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  wishListEndPoint: string;

  constructor(private httpClient:HttpClient) {
    this.wishListEndPoint='http://ec2-52-53-207-213.us-west-1.compute.amazonaws.com:8080/wishlist'

   }

  /*addToWishlist(Job:any) {
    const url=this.wishListEndPoint
    return this.httpClient.post(url,id)
  }
  removeFromWishlist(){
    return this.httpClient.delete(url+)
  }*/
}
