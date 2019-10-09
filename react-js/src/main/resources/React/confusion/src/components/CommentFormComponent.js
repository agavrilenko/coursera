import React, {Component} from 'react';
import {Breadcrumb, BreadcrumbItem, Button,Label,Row,  Col,
Modal, ModalHeader, ModalBody,Form, FormGroup, Input} from 'reactstrap';
import { Link} from 'react-router-dom';
import {Control, LocalForm, Errors} from 'react-redux-form';

const required = (val) => val && val.length;
const maxLength = (len) => (val) => !(val) || (val.length <= len);
const minLength = (len) => (val) => val && (val.length >= len);

class CommentForm extends Component {
    constructor(props){
            super(props);
            this.state = {
                isModalOpen: false
            };
            this.toggleModal = this.toggleModal.bind(this);
        }

        toggleModal() {
            this.setState({
              isModalOpen: !this.state.isModalOpen
            });
          }

    render(){
        return(
        <React.Fragment>
        <Modal isOpen={this.state.isModalOpen} toggle={this.toggleModal}>
                          <ModalHeader toggle={this.toggleModal}>Login</ModalHeader>
                          <ModalBody>
                                <Form onSubmit={this.handleLogin}>
                                    <FormGroup>
                                        <Label htmlFor="username">Username</Label>
                                        <Input type="text" id="username" name="username"
                                            innerRef={(input) => this.username = input} />
                                    </FormGroup>
                                    <FormGroup>
                                        <Label htmlFor="password">Password</Label>
                                        <Input type="password" id="password" name="password"
                                            innerRef={(input) => this.password = input}  />
                                    </FormGroup>
                                    <FormGroup check>
                                        <Label check>
                                            <Input type="checkbox" name="remember"
                                            innerRef={(input) => this.remember = input}  />
                                            Remember me
                                        </Label>
                                    </FormGroup>
                                    <Button type="submit" value="submit" color="primary">Login</Button>
                                </Form>
                          </ModalBody>
                      </Modal>
        </React.Fragment>
        );



    }

}
export default CommentForm;
