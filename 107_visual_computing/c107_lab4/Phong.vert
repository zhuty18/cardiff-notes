#version 330 core

layout(location = 0) in vec4 vPosition;
layout(location = 1) in vec3 vNormal;

out vec3 N;
out vec3 ecPosition;

uniform mat4 NormalTransform;
uniform mat4 ModelView;
uniform mat4 Projection;

void main(){
    // Transform vertex position into eye coordinates
    ecPosition = (ModelView * vPosition).xyz;
    // Transform vertex normal into eye coordinates
    N = normalize((NormalTransform *vec4(vNormal,0)).xyz);
    gl_Position = Projection * ModelView * vPosition;
}