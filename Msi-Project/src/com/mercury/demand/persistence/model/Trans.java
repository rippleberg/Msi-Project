package com.mercury.demand.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import sun.util.calendar.BaseCalendar.Date;

public class Trans {
	
	@Entity
	@Table(name="login")
	public class Trands implements java.io.Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2748948831285204760L;
		private int tid;
		private int lid;
		private Date  t_time; 
		private int price;
		private int quantity;
		private char t_type;
		private char t_status;
		
		 @Id
		 @Column(name="tid", nullable = false)
		public int getTid() {
			return tid;
		}
		public void setTid(int tid) {
			this.tid = tid;
		}
		public int getLid() {
			return lid;
		}
		public void setLid(int lid) {
			this.lid = lid;
		}
		public Date getT_time() {
			return t_time;
		}
		public void setT_time(Date t_time) {
			this.t_time = t_time;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public char getT_type() {
			return t_type;
		}
		public void setT_type(char t_type) {
			this.t_type = t_type;
		}
		public char getT_status() {
			return t_status;
		}
		public void setT_status(char t_status) {
			this.t_status = t_status;
		}
	 
}

}